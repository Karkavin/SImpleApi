package ru.touchit.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.touchit.office.dao.OfficeDao;
import ru.touchit.office.exception.NoSuchOfficeException;
import ru.touchit.office.model.Office;
import ru.touchit.office.view.BaseOfficeView;
import ru.touchit.office.view.FilterOfficeView;
import ru.touchit.office.view.FilterResultOfficeView;
import ru.touchit.office.view.FullOfficeView;
import ru.touchit.organisation.dao.OrganisationDao;
import ru.touchit.organisation.exception.NoSuchOrganisationException;
import ru.touchit.organisation.model.Organisation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 * @autor Artyom Karkavin
 */
@Service("officeService")
public class OfficeServiceImpl implements OfficeService {
    OfficeDao officeDao;
    OrganisationDao organisationDao;

    /**
     * Конструктор
     * @param officeDao Dao для работы с офисами
     * @param organisationDao Dao для работы с организациями
     */
    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganisationDao organisationDao) {
        this.officeDao = officeDao;
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

            String name = officeView.getName();
            String phone = officeView.getPhone();
            if (name == null) {
                name = "";
            }
            if (phone == null) {
                phone = "";
            }

            if (officeView.getIsActive() == null) {
                return officeDao.findByOrgIdAndNameAndPhone(optional.get(), name, phone)
                        .stream()
                        .map(mapOffice())
                        .collect(Collectors.toList());
            } else {
                return officeDao.findByOrgIdAndNameAndPhoneAndIsActive(optional.get(), name, phone, officeView.getIsActive())
                        .stream()
                        .map(mapOffice())
                        .collect(Collectors.toList());
            }
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