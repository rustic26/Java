package rustic26.java.clustering.sample;

public class ArySample implements ISample, IDimension<Double>
{
	public ArySample(double[] ary)
	{
		_ary = ary;
	}

	@Override
	public Double at(int i)
	{
		return _ary[i];
	}
	
	@Override
	public double diff(ISample other)
	{
		if(other instanceof ArySample)
		{
			ArySample as = (ArySample)other;
			
			if(as._ary.length == _ary.length)
			{
				double result = 0;
				for(int i = 0; i < _ary.length; ++i)
				{
					result += Math.pow(_ary[i] - as._ary[i], 2);
				}
				return Math.sqrt(result);
			}
		}
		return Double.MIN_VALUE;
	}

	@Override
	public Object getValue()
	{
		return _ary;
	}
	
	@Override
	public int size()
	{
		return _ary.length;
	}

	private double[] _ary;
}
