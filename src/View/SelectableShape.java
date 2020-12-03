package View;

public abstract class SelectableShape implements SceneShape
{
    public void setSelected(boolean b)
    {
        selected = b;
    }

    public boolean isSelected()
    {
        return selected;
    }


    private boolean selected;
}

