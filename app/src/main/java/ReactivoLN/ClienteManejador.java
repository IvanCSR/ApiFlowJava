
package ReactivoLN;

import DAO.ClientesDAO;
import Modelo.Clientes;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ClienteManejador extends SubmissionPublisher<Clientes>
        implements Subscriber<Clientes>{

    private DefaultTableModel modeloTB;
    private Subscription suscripcion;

    public ClienteManejador(DefaultTableModel modeloTB) {
        this.modeloTB = modeloTB;
    }
    public boolean Agregar(Clientes cliente) {
        var res=ClientesDAO.guardar(cliente);
        submit(cliente);
        return res;
    }
    public boolean Actualizar(Clientes cliente) {
        var res=ClientesDAO.actualizar(cliente);
        submit(cliente);
        return res;
    }
    public boolean Eliminar(Clientes cliente) {
        var res=ClientesDAO.eliminar(cliente);
        submit(cliente);
        return res;
    }
    private void ActualizarTabla(List<Clientes> lista){
        SwingUtilities.invokeLater(()->{
            modeloTB.getDataVector().removeAllElements();
            modeloTB.fireTableDataChanged();
            lista.forEach(ele-> modeloTB.addRow(ele.getFilaDatos()));
        });
    }
    public void ListarByDNI(String dni) {      
        ActualizarTabla(ClientesDAO.listaClientes(dni));
    }
    //Metodos se suscripcion
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.suscripcion = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Clientes item) {//cada que se agregar,actualiza o elimina un cliente
        ActualizarTabla(ClientesDAO.listaClientes());
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
