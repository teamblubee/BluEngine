package com.blubee.bluengine.entitysystem;



public class Position extends Component{

	public float x, y, z, px, py, pz;
	public double alpha;
	public float beta;
	
	public Position()
	{
		x = y = z = px = py = pz = 0;
		alpha = 0;
		beta = 0;
	}
	
	public Position(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position(float[] vals)
	{
		this.x = vals[0];
		this.y = vals[1];
		this.z = vals[2];
	}
	
	public void setValues(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public String toString()
	{
		return "Position ( "+x+", "+y+", "+z+" )";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + EntityID;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (EntityID != other.EntityID)
			return false;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

	
}
