/**
 * 
 */
package shared.simulation.config;

/**
 * @author Sonal
 *
 */
public enum ManipulatingParameterName
{
	a(1, 2), 
	x(2, 1), 
	ax(3, 1),
	y(4, 2),
	e(5, 2),
	b0(6, 2),
	k(7, 1),
	r(8, 1),
	q(9, 2),
	d(10, 2),
	bx(11, 1),
	s(12, 2),
	ca(13, 2),
	co(14, 2),
	pr(15, 2),
	ef(16, 2),
	eo(17, 2),
	fl(18, 0),
	tu(19, 1),
	su(20, 1),
	b0n(21, 2),
	cn(22, 2);

	private final int paramIndex;
	private final int paramType;

	private ManipulatingParameterName(int paramIndex, int paramType)
	{
		this.paramIndex = paramIndex;
		this.paramType = paramType;
	}

	public int getManipulatingParameterIndex()
	{
		return paramIndex;
	}
	
	public int getManipulatingParameterType()
	{
		return paramType;
	}
}
