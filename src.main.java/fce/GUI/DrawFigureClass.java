package fce.GUI;

public class DrawFigureClass {
	private DrawFigure drawFigure = DrawFigure.NONE;
	public DrawFigureClass(DrawFigure df){
		
	}
	public void setDrawFigure(DrawFigure drawFigure){
		this.drawFigure = drawFigure;
	}
	public DrawFigure getDrawFigure(){
		return drawFigure;
	}
}
