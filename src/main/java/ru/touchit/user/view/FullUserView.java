package ru.touchit.user.view;

import ru.touchit.user.model.User;

import javax.validation.constraints.NotNull;

public class FullUserView extends BaseUserView {
    @NotNull
    private long id;

    public FullUserView() {

    }

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