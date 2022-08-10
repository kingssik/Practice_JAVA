package controller;

public abstract class Controller {
	public String cmd;
	public abstract void doAction(String cmd, String actionMethodName);
}
