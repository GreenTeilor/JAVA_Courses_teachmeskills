package by.teachmeskills.homeworks.hw_31032023.ioStreamsAndCollections;

public class ValidatorUtil {
    private ValidatorUtil() {

    }

    private enum State {
        INVALID_LENGTH("Invalid length"), INVALID_NUMBER("Invalid number"), INVALID_NAME("Invalid name"), SUCCESS("Success");
        private final String state;

        State(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return state;
        }
    }

    public static class StateAndCode {
        private State state;
        private int code;

        {
            state = State.SUCCESS;
            code = 0;
        }

        public StateAndCode() {

        }

        public StateAndCode(State state, int code) {
            this.state = state;
            this.code = code;
        }

        @Override
        public String toString() {
            return switch (state) {
                case INVALID_LENGTH -> "Length of the document is not 15, but " + code;
                case INVALID_NAME -> "Document name does not match the \"docnum\" or \"contract\"";
                case INVALID_NUMBER -> "Document number is incorrect, position of first invalid character: " + code;
                default -> "Name is valid";
            };
        }
    }

    public static StateAndCode isValidDocument(String documentNumber) {
        if (documentNumber.length() != 15) {
            return new StateAndCode(State.INVALID_LENGTH, documentNumber.length()); //If length is invalid, return {INVALID_LENGTH, [actualLength]}
        } else if (!documentNumber.startsWith("docnum") && !documentNumber.startsWith("contract")) {
            return new StateAndCode(State.INVALID_NAME, 1); //If name is invalid, return {INVALID_NAME, 1}
        } else if (!documentNumber.matches("(docnum)|(contract)([A-Z]|[a-z]|[0-9])+")) {
            for (int i = 6; i < documentNumber.length(); ++i) {
                char character = documentNumber.charAt(i);
                if (!(character >= 'a' && character <= 'z') && !(character >= 'A' && character <= 'Z') && !(character >= '0' && character <= '9')) {
                    return new StateAndCode(State.INVALID_NUMBER, i); //If name is invalid, return {INVALID_NUMBER, [firstInvalidCharacterPosition]}
                }
            }
        }
        return new StateAndCode(); //Return {SUCCESS, 0}
    }

}
