package com.blubee.bluengine.entitysystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class EntityManager {

	public List<Integer> allEntities;
	public ConcurrentHashMap<Class<?>, ArrayList<Component>> allComps;
	public ArrayList<Component> temp;
	public Random rand;
	public int place, holder;

	public EntityManager() {
		allEntities = new ArrayList<Integer>();
		allComps = new ConcurrentHashMap<Class<?>, ArrayList<Component>>();
		rand = new Random();
	}
	
	public int createEntity()
	{
		place = rand.nextInt();
		//Log.v("eman", "creating entity "+place);
		return place;
	}

	public <T extends Component> void addComponent(int entity, T component) {

		if (allComps.get(component.getClass()) == null) {
			temp = new ArrayList<Component>();
			component.EntityID = entity;
			temp.add(component);
			allComps.put(component.getClass(), temp);
			// Log.v("eman ",
			// component.getClass().getSimpleName()+" was null added component "+component.getClass().getSimpleName());
		} else {
			temp = allComps.get(component.getClass());
			place = temp.size();
			for (int i = 0; i < place; i++) {
				if (temp.get(i).EntityID == entity) {
					// System.out.println("duplicate found "+entity+" already has "+component.getClass().getSimpleName());
					return;
				}
			}
			allComps.get(component.getClass()).add(component);
			// Log.v("eman",
			// "added "+component.getClass().getSimpleName()+" to "+entity+"'s component store ");
		}
	}

	public <T extends Component> ArrayList<? extends Component> getComponentOfType(
			Class<?> component) {
		return allComps.get(component);
	}

	public <T extends Component> void removeComponent(int entity, T component) {
		temp = allComps.get(component.getClass());
		holder = temp.size();
		for (int i = 0; i < holder; i++) {
			Component c = temp.get(i);
			if (c.EntityID == entity) {
				allComps.remove(c);
				// Log.v("eman",
				// "removed "+component.getClass().getSimpleName());
			}
		}
	}
}
