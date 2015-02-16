package com.gl.training.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class Page<T extends Page<T>> extends LoadableComponent<T> {

    protected final Logger log = LogManager.getLogger(this);
    protected final WebDriver wd;

    public Page(WebDriver wd) {
        this.wd = wd;
        PageFactory.initElements(wd, this);
    }

    public abstract String getPageURL();

    protected abstract void checkUniqueElements() throws Error;

    @Override
    protected void load() {
        log.info("Loading page: {}", getPageURL());
        wd.get(getPageURL());
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertThat("Wrong page URL", wd.getCurrentUrl(), Matchers.equalToIgnoringCase(getPageURL()));
        checkUniqueElements();
    }
}
