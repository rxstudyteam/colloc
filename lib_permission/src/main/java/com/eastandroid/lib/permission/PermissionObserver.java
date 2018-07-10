package com.eastandroid.lib.permission;

import java.util.Observable;

public final class PermissionObserver extends Observable {

	private static PermissionObserver INSTANCE = new PermissionObserver();
	private PermissionObserver() {
	}

	public static PermissionObserver getInstance() {
		return INSTANCE;
	}

	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}
}
