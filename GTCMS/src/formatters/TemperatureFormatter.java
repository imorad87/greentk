package formatters;

import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class TemperatureFormatter extends DefaultFormatterFactory {

	private static final long serialVersionUID = 8360385388525786837L;

	private DecimalFormat decimal;
	private NumberFormatter formatter;

	public TemperatureFormatter(int type) {

		decimal = new DecimalFormat();
		decimal.setMaximumFractionDigits(10);
		formatter = new NumberFormatter(decimal);

		setDefaultFormatter(formatter);
		setDisplayFormatter(new DisplayFormatter(type));
		setEditFormatter(formatter);
		setNullFormatter(formatter);

	}

	private class DisplayFormatter extends AbstractFormatter {
		private static final long serialVersionUID = 692827269392171360L;

		private static final int PLUS_MINUES_FORMATTER = 0;
		private static final int NORMAL_FORMATTER = 1;

		private int type;

		public DisplayFormatter(int type) {
			this.setType(type);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			String string = String.valueOf(value);
			switch (getType()) {

			case PLUS_MINUES_FORMATTER:

				return "± " + string + " \u2103";

			case NORMAL_FORMATTER:

				return string + " \u2103";

			default:
				return "0 \u2103";

			}

		}

		@Override
		public Object stringToValue(String text) throws ParseException {

			switch (getType()) {
			case PLUS_MINUES_FORMATTER:
				
				/*if (text == null || text.isEmpty()) {
					return null;
				}
				String token;

				StringTokenizer st = new StringTokenizer(text, "± ");
				token = (String) st.nextElement();

				double parseDouble = Double.parseDouble(token);
*/
				return text;
				
			case NORMAL_FORMATTER:
			
				return text;
			default:
				return 0;
			}

			

		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}
	}
}
