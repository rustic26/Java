package rustic26.java.clustering.sample;

public interface IDimension<T extends Comparable<T>>
{
	T at(int i);
	int size();
}
