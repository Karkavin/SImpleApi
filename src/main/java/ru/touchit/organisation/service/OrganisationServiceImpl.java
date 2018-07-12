package ru.touchit.organisation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.touchit.organisation.dao.OrganisationDao;
import ru.touchit.organisation.exception.NoSuchOrganisationException;
import ru.touchit.organisation.model.Organisation;
import ru.touchit.organisation.view.BaseOrganisationView;
import ru.touchit.organisation.view.FilterOrganisationView;
import ru.touchit.organisation.view.FilterResultOrganisationView;
import ru.touchit.organisation.view.FullOrganisationView;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("organisationService")
public class OrganisationServiceImpl implements OrganisationService {
    OrganisationDao organisationDao;

    @Autowired
    public OrganisationServiceImpl(OrganisationDao organisationDao) {
        this.organisationDao = organisationDao;
    }

    @Override
    @Transactional(readOnly = true)
    public FullOrganisationView getById(Long id) throws NoSuchOrganisationException {
        Optional<Organisation> optional = organisationDao.findById(id);

        if (!optional.isPresent()) {
            throw new NoSuchOrganisationException("No such organisation with id " + id);
        } else{
            return new FullOrganisationView(optional.get());
        }
    }

    @Override
    @Transactional
    public void add(BaseOrganisationView organisationView) {
        organisationDao.save(new Organisation(
                organisationView.getName(),
                organisationView.getFullName(),
                organisationView.getInn(),
                organisationView.getKpp(),
                organisationView.getAddress(),
                organisationView.getPhone(),
                organisationView.getIsActive()
        ));
    }

    @Override
    @Transactional
    public void update(FullOrganisationView organisationView) throws NoSuchOrganisationException {
        Optional<Organisation> optional = organisationDao.findById(organisationView.getId());

        if (!optional.isPresent()) {
            throw new NoSuchOrganisationException("No such organisation with id " + organisationView.getId());
        } else {
            Organisation organisationFromDB = optional.get();
            organisationFromDB.setName(organisationView.getName());
            organisationFromDB.setFullName(organisationView.getFullName());
            organisationFromDB.setInn(organisationView.getInn());
            organisationFromDB.setKpp(organisationView.getKpp());
            organisationFromDB.setAddress(organisationView.getAddress());
            organisationFromDB.setPhone(organisationView.getPhone());
            organisationFromDB.setIsActive(organisationView.getIsActive());

            organisationDao.save(organisationFromDB);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilterResultOrganisationView> filter(FilterOrganisationView organisationView) {
        String inn = organisationView.getInn();
        if (inn == null) {
            inn = "";
        }

        if (organisationView.getIsActive() == null) {
            return organisationDao.findByNameAndInn(organisationView.getName(), inn)
                    .stream()
                    .map(mapOrganisation())
                    .collect(Collectors.toList());
        } else {
            return organisationDao.findByNameAndInnAndIsActive(organisationView.getName(), inn, organisationView.getIsActive())
                    .stream()
                    .map(mapOrganisation())
                    .collect(Collectors.toList());
        }
    }

    private Function<Organisation, FilterResultOrganisationView> mapOrganisation() {
        return o -> {
            FilterResultOrganisationView view = new FilterResultOrganisationView();
            view.setId(o.getId());
            view.setName(o.getName());
            view.setIsActive(o.getIsActive());

            return view;
        };
    }
}