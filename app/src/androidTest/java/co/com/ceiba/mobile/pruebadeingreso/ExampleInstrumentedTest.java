package co.com.ceiba.mobile.pruebadeingreso;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import co.com.ceiba.mobile.pruebadeingreso.view.Calificador;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("co.com.ceiba.mobile.pruebadeingreso", appContext.getPackageName());
    }
}
