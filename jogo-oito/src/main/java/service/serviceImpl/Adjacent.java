package service.serviceImpl;

import java.util.Objects;

import model.enumType.Keyboard;
import service.Edge;
import service.Vertex;

public class Adjacent implements Edge {

	private final Keyboard key;
	private final Vertex cell;

	public Adjacent(Keyboard key, Vertex cell) {
		this.key = key;
		this.cell = cell;
	}

	@Override
	public Keyboard getKey() {
		return this.key;
	}

	@Override
	public Vertex getCell() {
		return this.cell;
	}
	
    @Override
    public boolean equals(Object obj) {
        return Objects.equals(((Adjacent) obj).getKey(), this.getKey());
    }
	
}
