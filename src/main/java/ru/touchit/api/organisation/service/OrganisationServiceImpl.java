package ru.touchit.api.organisation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.touchit.api.organisation.dao.OrganisationDao;
import ru.touchit.api.organisation.dao.OrganisationFilterDao;
import ru.touchit.api.organisation.exception.NoSuchOrganisationException;
import ru.touchit.api.organisation.model.Organisation;
import ru.touchit.api.organisation.view.BaseOrganisationView;
import ru.touchit.api.organisation.view.FilterOrganisationView;
import ru.touchit.api.organisation.view.FilterResultOrganisationView;
import ru.touchit.api.organisation.view.FullOrganisationView;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 * @author Artyom Karkavin
 */
@Service("organisationService")
public class OrganisationServiceImpl implements OrganisationService {
    private final OrganisationDao organisationDao;
    private final OrganisationFilterDao organisationFilterDao;

    /**
     * Конструктор
     * @param organisationDao Dao для работы с организациями
     * @param organisationFilterDao Dao с фильтрами для работы с организациями
     */
    @Autowired
    public OrganisationServiceImpl(OrganisationDao organisationDao, OrganisationFilterDao organisationFilterDao) {
        this.organisationDao = organisationDao;
        this.organisationFilterDao = organisationFilterDao;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<FilterResultOrganisationView> filter(FilterOrganisationView organisationView) {
        return organisationFilterDao.filter(organisationView.getName(), organisationView.getInn(), organisationView.getIsActive())
                .stream()
                .map(mapOrganisation())
                .collect(Collectors.toList());
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