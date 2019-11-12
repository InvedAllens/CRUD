
package misc;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.*;

/**
 *
 * @author Ruben Angeles
 */
public class Render extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        
        if (o instanceof JButton) {
            JButton btn = (JButton)o;
            return btn;
        }
        
        return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
    }
}
