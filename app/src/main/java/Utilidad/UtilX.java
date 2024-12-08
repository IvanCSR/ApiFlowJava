
package Utilidad;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import javax.swing.JTextField;
import org.kordamp.ikonli.swing.FontIcon;


public class UtilX {
    public static FontIcon setIconoX(FontIcon icono, Color color, int size) {
        var iconX = icono;
        iconX.setIconColor(color);
        iconX.setIconSize(size);
        return iconX;
    }
    public static void AsignarPlaceHolder(JTextField control,String texto){
        control.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, texto);
    }
}
