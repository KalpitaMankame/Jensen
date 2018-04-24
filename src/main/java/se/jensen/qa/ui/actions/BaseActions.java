package se.jensen.qa.ui.actions;

import se.jensen.qa.helpers.JensenDriver;

public abstract class BaseActions {

	protected JensenDriver jensenDriver;

	public BaseActions(JensenDriver jensenDriver) {
		super();
		this.jensenDriver = jensenDriver;
	}

}
