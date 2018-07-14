package ru.touchit.api.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.touchit.api.office.dao.OfficeDao;
import ru.touchit.api.office.dao.OfficeFilterDao;
import ru.touchit.api.office.exception.NoSuchOfficeException;
import ru.touchit.api.office.model.Office;
import ru.touchit.api.office.view.BaseOfficeView;
import ru.touchit.api.office.view.FilterOfficeView;
import ru.touchit.api.office.view.FilterResultOfficeView;
import ru.touchit.api.office.view.FullOfficeView;
import ru.touchit.api.organisation.dao.OrganisationDao;
import ru.touchit.api.organisation.exception.NoSuchOrganisationException;
import ru.touchit.api.organisation.model.Organisation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 * @author Artyom Karkavin
 */
@Service("officeService")
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDao officeDao;
    private final OfficeFilterDao officeFilterDao;
    private final OrganisationDao organisationDao;

    /**
     * Конструктор
     * @param officeDao Dao для работы с офисами
     * @param officeFilterDao Dao с фильтрами для работы с офисами
     * @param organisationDao Dao для работы с организациями
     */
    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OfficeFilterDao officeFilterDao, OrganisationDao organisationDao) {
        this.officeDao = officeDao;
        this.officeFilterDao = officeFilterDao;
        this.organisationDao = organisationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public FullOfficeView getById(Long id) throws NoSuchOfficeException {
        Optional<Office> optional = officeDao.findById(id);

        if (!optional.isPresent()) {
            throw new NoSuchOfficeException("No such office with id " + id);
        } else {
            return new FullOfficeView(optional.get());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(BaseOfficeView officeView) throws NoSuchOrganisationException {
        Optional<Organisation> optional = organisationDao.findById(officeView.getOrgId());

        if (!optional.isPresent()) {
            throw new NoSuchOrganisationException("No such organisation with id " + officeView.getOrgId());
        } else {
            officeDao.save(new Office(
                    optional.get(),
                    officeView.getName(),
                    officeView.getAddress(),
                    officeView.getPhone(),
                    officeView.getIsActive()
            ));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(FullOfficeView officeView) throws NoSuchOfficeException, NoSuchOrganisationException {
        Optional<Office> optionalOffice = officeDao.findById(officeView.getId());
        Optional<Organisation> optionalOrganisation = organisationDao.findById(officeView.getOrgId());

        if (!optionalOffice.isPresent()) {
            throw new NoSuchOfficeException("No such office with id " + officeView.getId());
        } else if (!optionalOrganisation.isPresent()) {
            throw new NoSuchOrganisationException("No such organisation with id " + officeView.getOrgId());
        } else{
            Office officeFromDB = optionalOffice.get();
            officeFromDB.setOrganisation(optionalOrganisation.get());
            officeFromDB.setName(officeView.getName());
            officeFromDB.setAddress(officeView.getAddress());
            officeFromDB.setPhone(officeView.getPhone());
            officeFromDB.setIsActive(officeView.getIsActive());

            officeDao.save(officeFromDB);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<FilterResultOfficeView> filter(FilterOfficeView officeView) throws NoSuchOrganisationException {
        Optional<Organisation> optional = organisationDao.findById(officeView.getOrgId());

        if (!optional.isPresent()) {
            throw new NoSuchOrganisationException("No such organisation with id " + officeView.getOrgId());
        } else {
            return officeFilterDao.filter(optional.get(), officeView.getName(), officeView.getPhone(), officeView.getIsActive())
                    .stream()
                    .map(mapOffice())
                    .collect(Collectors.toList());
        }
    }

    private Function<Office, FilterResultOfficeView> mapOffice() {
        return o -> {
            FilterResultOfficeView view = new FilterResultOfficeView();
            view.setId(o.getId());
            view.setName(o.getName());
            view.setIsActive(o.getIsActive());

            return view;
        };
    }
}