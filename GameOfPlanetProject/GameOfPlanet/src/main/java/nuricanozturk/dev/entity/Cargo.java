package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

public class Cargo implements GameContext
{
    private final Commodity m_commodity;
    private int quantityOfCommodity;

    public Cargo(Commodity commodity, int quantityOfCommodity)
    {
        m_commodity = commodity;
        this.quantityOfCommodity = quantityOfCommodity;
    }


    public Commodity getCommodity()
    {
        return m_commodity;
    }

    public int getQuantityOfCommodity()
    {
        return quantityOfCommodity;
    }

    public void setQuantityOfCommodity(int quantityOfCommodity)
    {
        this.quantityOfCommodity = quantityOfCommodity;
    }

    @Override
    public String toString()
    {
        return "Cargo{" +
                "m_commodity=" + m_commodity +
                ", quantityOfCommodity=" + quantityOfCommodity +
                "}\n";
    }
}
