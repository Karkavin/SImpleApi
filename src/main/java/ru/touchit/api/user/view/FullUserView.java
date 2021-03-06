package ru.touchit.api.user.view;

import ru.touchit.api.user.model.User;

import javax.validation.constraints.NotNull;

/**
 * View для представления подробной информации о сотруднике {@link User}
 * @author Artyom Karkavin
 */
public class FullUserView extends BaseUserView {
    /** Поле: идентификатор */
    @NotNull
    private long id;

    /**
     * Конструктор
     */
    public FullUserView() {

    }

    /**
     * {@inheritDoc}
     */
    public FullUserView(User user) {
        super(user);
        this.id = user.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}