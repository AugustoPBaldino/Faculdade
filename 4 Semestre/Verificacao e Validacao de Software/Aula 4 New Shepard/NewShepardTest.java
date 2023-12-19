import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import org.quicktheories.QuickTheory;
import org.quicktheories.api.Tuple3;
import org.quicktheories.api.Tuple4;
import org.quicktheories.core.Gen;

public class NewShepardTest {

    @ParameterizedTest
    @MethodSource("validCandidates")
    public void testAutorizaParaCandidatosVálidos(float peso, float altura, float tempoEscadas) {
        assertTrue(NewShepard.autoriza(peso, altura, tempoEscadas));
    }

    @ParameterizedTest
    @MethodSource("invalidCandidates")
    public void testAutorizaParaCandidatosInválidos(float peso, float altura, float tempoEscadas) {
        assertThrows(IllegalArgumentException.class, () -> NewShepard.autoriza(peso, altura, tempoEscadas));
    }

    public static Gen<Tuple3<Float, Float, Float>> validCandidates() {
        return pesoAlturaTempoGenerator()
                .assuming(t -> t._1 >= 50f && t._1 <= 101f) 
                .assuming(t -> t._2 >= 1.52f && t._2 <= 1.93f)
                .assuming(t -> t._3 >= 0f && t._3 < 80f); 
    }

    public static Gen<Tuple3<Float, Float, Float>> invalidCandidates() {
        return pesoAlturaTempoGenerator()
                .assuming(t -> t._1 < 50f || t._1 > 101f) 
                .assuming(t -> t._2 < 1.52f || t._2 > 1.93f)
                .assuming(t -> t._3 < 0f || t._3 >= 80f); 
    }

    public static Gen<Tuple3<Float, Float, Float>> pesoAlturaTempoGenerator() {
        return
                Gen.floats().zip(Gen.floats(), Gen.floats(), Tuple3::new);
    }
}
