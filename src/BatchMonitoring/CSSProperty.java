package BatchMonitoring;

import java.awt.Color;

public class CSSProperty
{
	private static CSSProperty properties;

	public static Color COLOR_WRAPPER_BATCH_PANEL = new Color(0x293949);

	public static float SIZE_FAILTAG = 23f;
	public static Color COLOR_FAILTAG = new Color(0XFFFFFF);
	public static Color COLOR_FAILCOUNT = new Color(0xFFFFFF);
	public static Color COLOR_FAIL_BACKGROUND = new Color(0xE26061);

	public static float SIZE_PASSTAG = 23f;
	public static Color COLOR_PASS_TAG = new Color(0xFFFFFF);
	public static Color COLOR_PASSCOUNT = new Color(0xFFFFFF);
	public static Color COLOR_PASS_BACKGROUND = new Color(0x1ABC9C);

	public static Color COLOR_PROGRESS_BAR_TEXT = Color.white;
	public static Color COLOR_PROGRESS_BAR_BACKGROUND = new Color(0x293949);
	public static Color COLOR_PROGRESS_BAR_TRACK = new Color(0x7F919C);
	public static Color COLOR_PROGRESS_BAR_SECTOR = new Color(0x5CFFAC);

	private CSSProperty()
	{

	}

	public static CSSProperty loadCSSProperty()
	{
		if (CSSProperty.properties == null)
		{
			CSSProperty.properties = new CSSProperty();
		}
		return CSSProperty.properties;
	}
}
