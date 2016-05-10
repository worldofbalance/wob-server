/**
 * 
 */
package cos.simulation.config;

/**
 * @author Sonal
 *
 */
public enum ModelType
{
	EMPIRICAL_MODEL(0), 
	NICHE_MODEL(1), 
	NICHE_MODEL_WITH_PARASITES(2), 
	RELAXED_NICHE_MODEL(3), 
	NESTED_HIERARCHY_MODEL(4), 
	CASCADE_MODEL(5), 
	RANDOM_BETA_MODEL(6), 
	RANDOM_MODEL(7), 
	NICHE_2D_MODEL(8);

	private final int typeIndex;

	private ModelType(int typeIndex)
	{
		this.typeIndex = typeIndex;
	}

	public int getModelType()
	{
		return typeIndex;
	}	
}
