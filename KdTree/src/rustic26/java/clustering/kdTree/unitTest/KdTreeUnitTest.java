package rustic26.java.clustering.kdTree.unitTest;

import java.util.*;

import junit.framework.Assert;

import org.junit.Test;

import rustic26.java.clustering.kdTree.*;
import rustic26.java.clustering.sample.*;

public class KdTreeUnitTest
{
	@Test
	public void treeDepthTest()
	{
		List<IDimension<Double>> list = new ArrayList<IDimension<Double>>();
		list.add(new ArySample(new double[]{ 0 }));
		KdTreeNode<Double> node = new KdTreeNode<Double>(list);
		
		Assert.assertSame(0, node.getDepth());
	}
	
	@Test
	public void treeStructureTest()
	{
		Collection<IDimension<Double>> set = 
				new HashSet<IDimension<Double>>();
		
		for(double i = 0; i < 10; ++i)
		{
			set.add(new ArySample(new double[] {i, (i + 1) % 10, (i + 2) % 10, (i + 3) % 10}));
		}
		
		KdTreeNode<Double> node = new KdTreeNode<Double>(set);
		
		Assert.assertTrue(verifyTree(node, 0));
	}
	
	private static<T extends Comparable<T>> boolean verifyTree(KdTreeNode<T> node, int at)
	{
		int dim = node.getValue().size();
		T value = node.getValue().at(at);
		
		if(node.getLarger() != null)
		{
			if(!verifyTree(node.getLarger(), (at + 1) % dim))
			{
				return false;
			}
			T min = min(node.getLarger(), at);
			if(value.compareTo(min) >= 0)
			{
				return false;
			}
		}
		if(node.getLess() != null)
		{
			if(!verifyTree(node.getLess(), (at + 1) % dim))
			{
				return false;
			}
			T max = max(node.getLess(), at);
			if(value.compareTo(max) < 0)
			{
				return false;
			}
		}
		return true;
	}
	
	private static<T extends Comparable<T>> T min(KdTreeNode<T> node, int at)
	{
		T result = node.getValue().at(at);
		
		if(node.getLarger() == null && node.getLess() == null)
		{
			return result;
		}
		
		if(node.getLarger() != null)
		{
			T largerMin = min(node.getLarger(), at);
			if(result.compareTo(largerMin) > 0)
			{
				result = largerMin;
			}
		}
		
		if(node.getLess() != null)
		{
			T lessMin = min(node.getLess(), at);
			if(result.compareTo(lessMin) > 0)
			{
				result = lessMin;
			}
		}
		
		return result;
	}

	private static<T extends Comparable<T>> T max(KdTreeNode<T> node, int at)
	{
		T result = node.getValue().at(at);
		
		if(node.getLarger() == null && node.getLess() == null)
		{
			return result;
		}
		
		if(node.getLarger() != null)
		{
			T largerMin = max(node.getLarger(), at);
			if(result.compareTo(largerMin) < 0)
			{
				result = largerMin;
			}
		}
		
		if(node.getLess() != null)
		{
			T lessMin = max(node.getLess(), at);
			if(result.compareTo(lessMin) < 0)
			{
				result = lessMin;
			}
		}
		
		return result;
	}
}
