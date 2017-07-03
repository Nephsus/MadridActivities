package kc.dcava.domain;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;

import kc.dcava.domain.managers.db.DBConstants;
import kc.dcava.domain.managers.db.MyActivityDao;
import kc.dcava.domain.managers.db.MyActivityDaoImpl;
import kc.dcava.domain.model.MyActivity;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DaoTest {

    private static Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void insert_several_activities(){
        MyActivityDao<MyActivity> dao = new MyActivityDaoImpl( appContext );

        dao.deleteAll();

        //Insertamos
        for (MyActivity myactitivity:fakeGeneratorAtivities()) {
            dao.insert(myactitivity);
        }

        LinkedList<MyActivity> result = dao.query();

        assertEquals(result.size(), 5);

    }


    @Test
    public void insert_success(){
        MyActivityDao<MyActivity> dao = new MyActivityDaoImpl( appContext );

        //Insertamos
        for (MyActivity myactitivity:fakeGeneratorAtivities()) {
            dao.insert(myactitivity);
        }

        LinkedList<MyActivity> result = dao.query();

        assertEquals(result.get(0).getName(), "MUSEO DEL PRADO");

    }


    @Test
    public void really_delete_all_records_table_database() throws Exception {
        MyActivityDao<MyActivity> dao = new MyActivityDaoImpl( appContext );

        //Insertamos
        for (MyActivity myactitivity:fakeGeneratorAtivities()) {
            dao.insert(myactitivity);
        }

        //Y borramos para ver que todo funciona
        dao.deleteAll();

        LinkedList<MyActivity> result = dao.query();

        assertNull(result);

    }


    @Test
    public void operator_like_usind_field_of_database() throws Exception {
        MyActivityDao<MyActivity> dao = new MyActivityDaoImpl( appContext );

        //Inicializamos la bbdd
        dao.deleteAll();

        for (MyActivity myactitivity:fakeGeneratorAtivities()) {
            dao.insert(myactitivity);
        }


        LinkedList<MyActivity> result = dao.query("UPPER(" + DBConstants.KEY_MYACTIVITY_NAME + ") LIKE ?", new String[]{"%muse%" }, DBConstants.KEY_MYACTIVITY_ID);


        assertEquals(result.size(), 2);

    }


    private MyActivity[] fakeGeneratorAtivities(){
        MyActivity[] myActivities = new MyActivity[ 5 ];

        myActivities[0] = new MyActivity(1,"MUSEO DEL PRADO","DFDA","DFASDF","DFDSFSAD","DSAFASD","DFASDFSA",938.4f,53.0f);
        myActivities[1] = new MyActivity(1,"CASA AMERICA","DFDA","DFASDF","DFDSFSAD","DSAFASD","DFASDFSA",945.4f,52.0f);
        myActivities[2] = new MyActivity(1,"ESTATUA DE CIBELES","DFDA","DFASDF","DFDSFSAD","DSAFASD","DFASDFSA",933.4f,51.0f);
        myActivities[3] = new MyActivity(1,"MUSEO DEL JAMON","DFDA","DFASDF","DFDSFSAD","DSAFASD","DFASDFSA",838.4f,51.0f);
        myActivities[4] = new MyActivity(1,"PARQUE CALERO","DFDA","DFASDF","DFDSFSAD","DSAFASD","DFASDFSA",638.4f,57.0f);

        return myActivities;
    }



}
