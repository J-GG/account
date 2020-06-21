package fr.jg.account.dto;

import org.springframework.hateoas.RepresentationModel;

public class LinkedResourceArray extends RepresentationModel<LinkedResourceArray> {

    private int count;

    public LinkedResourceArray(final int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(final int count) {
        this.count = count;
    }
}
