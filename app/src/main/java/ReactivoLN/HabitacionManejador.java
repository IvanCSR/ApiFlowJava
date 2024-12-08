
package ReactivoLN;

import DAO.HabitacionesDAO;
import Modelo.Habitaciones;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IvanCS
 */
public class HabitacionManejador extends SubmissionPublisher<Habitaciones>
        implements Flow.Subscriber<Habitaciones> {

    private DefaultTableModel modeloTB;
    private Flow.Subscription suscripcion;
    
     public HabitacionManejador(DefaultTableModel modeloTB) {
        this.modeloTB = modeloTB;
    }
    
    //Metodos propios
    private void ActualizarTabla(List<Habitaciones> lista){
        SwingUtilities.invokeLater(()->{
            modeloTB.getDataVector().removeAllElements();
            modeloTB.fireTableDataChanged();
            lista.forEach(ele-> modeloTB.addRow(ele.getFilaDatos()));
        });
    }
    public void ListarByNro(String nro) {      
        ActualizarTabla(HabitacionesDAO.listaHabitaciones(nro));
    }
    //
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
         this.suscripcion = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Habitaciones item) {
        ActualizarTabla(HabitacionesDAO.listaHabitaciones(""));
        suscripcion.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
         throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
          System.out.println("Hecho!");
    }
    
}
