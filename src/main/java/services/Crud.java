package services;

import java.util.List;

public interface Crud<T> {
	List<T> getAll();
	T findById(Integer id);
	T save(T item);
	void deleteById(Integer id);
}
