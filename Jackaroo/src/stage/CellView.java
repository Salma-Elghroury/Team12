package stage;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import engine.board.Cell;

public class CellView extends Circle {

	Cell cell;
	double x;
	double y;

	public CellView(Cell cell, double x, double y) {
		super(8);
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.setFill(Paint.valueOf("D9996B"));
		this.cell = cell;
		this.x = x;
		this.y = y;
	}

	public Cell getCell() {
		return cell;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
