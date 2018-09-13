package com.obliquity.beanexaminer;

public class ExaminerMaximumDepthReachedException extends Exception {
	private final String name;
	private final Object target;
	private final int maxdepth;
	
	private final String message;
	
	public ExaminerMaximumDepthReachedException(String name, Object target, int maxdepth) {
		this.name = name;
		this.target = target;
		this.maxdepth = maxdepth;
		
		message = "Maximum depth " + maxdepth + " reached whilst examining an object of class " + target.getClass().getName() +
				(name == null ? " with no name" : " named " + name);
	}
	
	public String getName() { return name; }
	
	public Object getTarget() { return target; }
		
	public int getMaximumDepth() { return maxdepth; }

	public String getMessage() { return message; }
}
