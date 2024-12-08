package ReactivoLN;

import DAO.ReservasDAO;
import Modelo.DetalleReservas;
import Modelo.Reservas;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IvanCS
 */
public class DetalleReservaManejador extends SubmissionPublisher<DetalleReservas>
        implements Flow.Subscriber<DetalleReservas> {

    private DefaultTableModel modeloTB;
    private Flow.Subscription suscripcion;
    private List<DetalleReservas> listaDetalle;
    private JLabel jlbltotal;

    public DetalleReservaManejador(DefaultTableModel modeloTB, List<DetalleReservas> listaDetalle,
            JLabel jlbltotal) {
        this.modeloTB = modeloTB;
        this.listaDetalle = listaDetalle;
        this.jlbltotal=jlbltotal;
    }

    //
     public boolean GuardarReserva(Reservas reserva) {
        var res=ReservasDAO.guardar(reserva);        
        return res;
    }
    public void AgregarElemento(DetalleReservas item) {
        listaDetalle.add(item);
        submit(item);
    }

    public void QuitarElemento(DetalleReservas item) {
        listaDetalle.removeIf(ele -> ele.Habitacion().IdHabitacion() == item.Habitacion().IdHabitacion());
        submit(item);
    }
    public void LimpiarElementos() {      
       listaDetalle.clear();
        ActualizarTabla(listaDetalle);
    }

    private void ActualizarTabla(List<DetalleReservas> lista) {
        SwingUtilities.invokeLater(() -> {
            modeloTB.getDataVector().removeAllElements();
            modeloTB.fireTableDataChanged();
            lista.forEach(ele -> modeloTB.addRow(ele.getFilaDatos()));
            var total = (float) listaDetalle.stream().mapToDouble(ele -> ele.Importe()).sum();
            jlbltotal.setText(String.format("%.2f", total));
        });
    }

    //
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.suscripcion = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(DetalleReservas item) {
        ActualizarTabla(listaDetalle);        
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
