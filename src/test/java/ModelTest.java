import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ModelTest {

    @Test
    public void settingLinearSquareFootPriceTest(){
        Model model = new Model();
        double price = 7.0;
        model.setLinearSquareFootPrice(price);
        assert model.linearSquareFootPrice == price;
    }

    @Test
    public void calculatingTotalPriceTest1(){
        Model model = new Model();
        model.setLinearSquareFootPrice(5.0);

        Side side1 = new Side(10.0);
        model.addSide(side1);

        Side side2 = new Side(8.0);
        model.addSide(side2);

        Gate gate = new Gate(100.0);
        model.addGate(gate);

        double taxPercentage = 0.13;
        double expectedTotalPrice = ((10.0 * 5.0) + (8.0 * 5.0) + 100.0) * (1.0 + taxPercentage);
        model.calculateTotalPrice(taxPercentage);
        double calculatedTotalPrice = model.getTotalPrice();
        assert calculatedTotalPrice == expectedTotalPrice;

    }

}
