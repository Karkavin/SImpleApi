package ru.touchit.office.view;

import ru.touchit.office.model.Office;

import javax.validation.constraints.NotNull;

public class NewOfficeView extends BaseOfficeView {
    @NotNull
    private long orgId;

    public NewOfficeView() {

    }

    public NewOfficeView(Office office) {
        super(office);

        this.orgId = office.getOrganisation().getId();
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }
}