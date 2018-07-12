package ru.touchit.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.touchit.office.dao.OfficeDao;
import ru.touchit.office.exception.NoSuchOfficeException;
import ru.touchit.office.model.Office;
import ru.touchit.office.view.FilterOfficeView;
import ru.touchit.office.view.FilterResultOfficeView;
import ru.touchit.office.view.FullOfficeView;
import ru.touchit.office.view.NewOfficeView;
import ru.touchit.organisation.dao.OrganisationDao;
import ru.touchit.organisation.exception.NoSuchOrganisationException;
import ru.touchit.organisation.model.Organisation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("officeService")
public class OfficeServiceImpl implements OfficeService {
    OfficeDao officeDao;
    OrganisationDao organisationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganisationDao organisationDao) {
        this.officeDao = officeDao;
        this.organisationDao = organisationDao;
    }

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

    @Override
    @Transactional
    public void add(NewOfficeView officeView) throws NoSuchOrganisationException {
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

    @Override
    @Transactional
    public void update(FullOfficeView officeView) throws NoSuchOfficeException {
        Optional<Office> optional = officeDao.findById(officeView.getId());

        if (!optional.isPresent()) {
            throw new NoSuchOfficeException("No such office with id " + officeView.getId());
        } else {
            Office officeFromDB = optional.get();
            officeFromDB.setName(officeView.getName());
            officeFromDB.setAddress(officeView.getAddress());
            officeFromDB.setPhone(officeView.getPhone());
            officeFromDB.setIsActive(officeView.getIsActive());

            officeDao.save(officeFromDB);
        }
    }

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