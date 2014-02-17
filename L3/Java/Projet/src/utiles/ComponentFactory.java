package utiles;


/**
 * This utility class is used to instantiate the different types of components.
 * The different methods are called by our custom library when building new
 * components in logisim. However, the implementation of these methods is
 * missing.
 * 
 * You have propose a class hierarchy based on the specification of the
 * Component interface which provides the implementation of the different
 * components and then complete the Factory with your implementation.
 * 
 */public class ComponentFactory {

		/**
		 * @return a NOT component
		 */
		public static Component createNotComponent() {
			return new Not();
			
		}

		/**
		 * @return an AND component
		 */
		public static Component createAndComponent() {
			return  new And();

		}
		
		/**
		 * @return an OR component
		 */
		public static Component createOrComponent() {
			return  new Or();

		}
		
		/**
		 * @return an MODULO3 component
		 */
		public static Component createModulo3Component() {
			return  new Modulo3();

		}
		
		/**
		 * @return an SOMME component
		 */
		public static Component createSommeComponent() {
			return  new Somme();

		}

	}