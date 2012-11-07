package com.blubee.bluengine.entitysystem;

public abstract class Component {
	public int EntityID;

	public abstract boolean equals(Object object);

	public abstract int hashCode();
}