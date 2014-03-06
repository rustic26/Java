package rustic26.java.clustering.sample.unitTest;

import org.junit.*;
import java.util.*;
import rustic26.java.clustering.sample.*;

public class SampleUnitTest
{
	@Test
	public void dimensionTest()
	{
		IDimension<Double> d = new ArySample(new double[]{0, 1, 2, 3, 4});
		
		for(int i = 0; i < 5; ++i)
		{
			Assert.assertEquals(Double.valueOf(i),  d.at(i));
		}
		
	}
	
	@Test
	public void diffTest()
	{
		ISample s1 = new ArySample(new double[]{1, -1});
		ISample s2 = new ArySample(new double[]{-1, 1});
		
		double diff = s1.diff(s2);
		Assert.assertEquals(Math.sqrt(2) * 2, diff, 0);
	}
	
	@Test
	public void boundrySampleTest()
	{
		Map<Double, ISample> map = new HashMap<>();
		for(double i = -5; i <= 5; ++i)
		{
			map.put(Double.valueOf(i), new ArySample(new double[]{i, i}));
		}
		
		ISample target = new ArySample(new double[]{6, 6});
		
		ISample closest = Util.boundrySample(map.values(), target, BoundryType.Closest);
		Assert.assertSame(closest, map.get(Double.valueOf(5)));
		
		ISample farest = Util.boundrySample(map.values(), target, BoundryType.Farest);
		Assert.assertSame(farest, map.get(Double.valueOf(-5)));	
	}
	
	@Test
	public void totalDistTest()
	{
		List<ISample> list = new ArrayList<>();
		for(double i = -5; i <= 5; ++i)
		{
			list.add(new ArySample(new double[]{i}));
		}
		
		ISample target = new ArySample(new double[]{6});
		double dist = Util.totalDist(list, target);
		
		Assert.assertEquals(66.0, dist, 0);
	}
}
