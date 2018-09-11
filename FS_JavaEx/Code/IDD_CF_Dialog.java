//------------------------------------------------------------------------------
// IDD_CF_Dialog.java:
//		Implementation of "control creator" class IDD_CF_Dialog
//------------------------------------------------------------------------------
import java.awt.*;
import DialogLayout;

public class IDD_CF_Dialog
{
	Container    m_Parent       = null;
	boolean      m_fInitialized = false;
	DialogLayout m_Layout;

	// Control definitions
	//--------------------------------------------------------------------------
	Label         Red_Label;
	Label         Green_Label;
	Label         Blue_Label;
	TextField     Red_Value;
	TextField     Green_Value;
	TextField     Blue_Value;
	Scrollbar     IDC_SCROLLBAR_Red;
	Scrollbar     IDC_SCROLLBAR_Green;
	Scrollbar     IDC_SCROLLBAR_Blue;
	Button        ID_Rnd_BUTTON1;


	// Constructor
	//--------------------------------------------------------------------------
	public IDD_CF_Dialog (Container parent)
	{
		m_Parent = parent;
	}

	// Initialization.
	//--------------------------------------------------------------------------
	public boolean CreateControls()
	{
		// CreateControls should be called only once
		//----------------------------------------------------------------------
		if (m_fInitialized || m_Parent == null)
			return false;

		// m_Parent must be extended from the Container class
		//----------------------------------------------------------------------
		if (!(m_Parent instanceof Container))
			return false;

		// Since a given font may not be supported across all platforms, it
		// is safe to modify only the size of the font, not the typeface.
		//----------------------------------------------------------------------
	    Font OldFnt = m_Parent.getFont();
	    if (OldFnt != null)
	    {
			Font NewFnt = new Font(OldFnt.getName(), OldFnt.getStyle(), 8);

    		m_Parent.setFont(NewFnt);
	    }

		// All position and sizes are in dialog logical units, so we use a
		// DialogLayout as our layout manager.
		//----------------------------------------------------------------------
		m_Layout = new DialogLayout(m_Parent, 209, 51);
		m_Parent.setLayout(m_Layout);
		m_Parent.addNotify();

		Dimension size   = m_Layout.getDialogSize();
		Insets    insets = m_Parent.insets();
		
		m_Parent.resize(insets.left + size.width  + insets.right,
                        insets.top  + size.height + insets.bottom);

		// Control creation
		//----------------------------------------------------------------------
		Red_Label = new Label ("Red", Label.RIGHT);
		m_Parent.add(Red_Label);
		m_Layout.setShape(Red_Label, 7, 10, 23, 8);

		Green_Label = new Label ("Green", Label.RIGHT);
		m_Parent.add(Green_Label);
		m_Layout.setShape(Green_Label, 7, 23, 23, 8);

		Blue_Label = new Label ("Blue", Label.RIGHT);
		m_Parent.add(Blue_Label);
		m_Layout.setShape(Blue_Label, 7, 38, 23, 8);

		Red_Value = new TextField ("00");
		m_Parent.add(Red_Value);
		m_Layout.setShape(Red_Value, 134, 6, 31, 13);

		Green_Value = new TextField ("00");
		m_Parent.add(Green_Value);
		m_Layout.setShape(Green_Value, 134, 20, 31, 13);

		Blue_Value = new TextField ("00");
		m_Parent.add(Blue_Value);
		m_Layout.setShape(Blue_Value, 134, 33, 31, 13);

		IDC_SCROLLBAR_Red = new Scrollbar (Scrollbar.HORIZONTAL, 0, 1, 0, 255);
		m_Parent.add(IDC_SCROLLBAR_Red);
		m_Layout.setShape(IDC_SCROLLBAR_Red, 34, 6, 94, 11);

		IDC_SCROLLBAR_Green = new Scrollbar (Scrollbar.HORIZONTAL, 0, 1, 0, 255);
		m_Parent.add(IDC_SCROLLBAR_Green);
		m_Layout.setShape(IDC_SCROLLBAR_Green, 34, 20, 94, 11);

		IDC_SCROLLBAR_Blue = new Scrollbar (Scrollbar.HORIZONTAL, 0, 1, 0, 255);
		m_Parent.add(IDC_SCROLLBAR_Blue);
		m_Layout.setShape(IDC_SCROLLBAR_Blue, 34, 35, 94, 11);

		ID_Rnd_BUTTON1 = new Button ("Random");
		m_Parent.add(ID_Rnd_BUTTON1);
		m_Layout.setShape(ID_Rnd_BUTTON1, 167, 15, 35, 20);

		m_fInitialized = true;
		return true;
	}
}
