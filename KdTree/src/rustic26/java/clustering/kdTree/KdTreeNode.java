package rustic26.java.clustering.kdTree;

import java.util.*;

import rustic26.java.clustering.sample.*;

public class KdTreeNode<T extends Comparable<T>>
{
	public KdTreeNode(Collection<IDimension<T>> nodes, int depth) throws NullPointerException
	{
		_depth = depth;
		
		Iterator<IDimension<T>> it = nodes.iterator();
		
		if(nodes == null || nodes.size() == 0)
		{
			throw new NullPointerException();
		}
		
		_value = it.next();
		
		List<IDimension<T>> largerList = new ArrayList<IDimension<T>>();
		List<IDimension<T>> lessList = new ArrayList<IDimension<T>>();
		
		while(it.hasNext())
		{
			IDimension<T> node = it.next();
			int c = _value.at(depth).compareTo(node.at(depth));
			if(c > 0)
			{
				largerList.add(node);
			}
			else
			{
				lessList.add(node);
			}
		}
		
		depth = depth == _value.size() ? 0 : depth + 1;
		
		if(largerList.size() != 0)
		{
			_larger = new KdTreeNode<T>(largerList, depth);
		}
		if(lessList.size() != 0)
		{
			_less = new KdTreeNode<T>(lessList, depth);
		}
	}
	
	public KdTreeNode(Collection<IDimension<T>> nodes) throws NullPointerException
	{
		this(nodes, 0);
	}
	
	public int getDepth()
	{
		return _depth;
	}
	
	public IDimension<T> getValue()
	{
		return _value;
	}
	
	public KdTreeNode<T> getLarger()
	{
		return _larger;
	}
	
	public KdTreeNode<T> getLess()
	{
		return _less;
	}
	
	private int _depth;
	private IDimension<T> _value;
	private KdTreeNode<T> _larger;
	private KdTreeNode<T> _less;
}
