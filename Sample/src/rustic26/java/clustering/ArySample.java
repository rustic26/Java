package rustic26.java.clustering;

public class ArySample implements ISample
{
	public ArySample(double[] ary)
	{
		_ary = ary;
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
				return result;
			}
		}
		return Double.MIN_VALUE;
	}

	@Override
	public Object getValue()
	{
		return _ary;
	}

	private double[] _ary;
}
