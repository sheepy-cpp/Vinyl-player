package backend.lib;

import java.util.*;

// Recursive-descent JSON parser. Single pass, no lookahead buffering.
public final class JsonParser {

    private final String json;
    private int pos; // current read position in the input

    private JsonParser(String json) {
        this.json = json;
    }

    // Entry point: parses the whole string and returns Map/List/String/Long/Double/Boolean/null.
    public static Object parse(String json) {
        JsonParser p = new JsonParser(json);
        p.skipWs();
        Object result = p.parseValue();
        p.skipWs();
        if (p.pos != json.length()) throw new IllegalArgumentException("Trailing data at " + p.pos);
        return result;
    }

    // Dispatches based on the next character to the right sub-parser.
    private Object parseValue() {
        char c = json.charAt(pos);
        return switch (c) {
            case '{' -> parseObject();
            case '[' -> parseArray();
            case '"' -> parseString();
            case 't', 'f' -> parseBoolean();
            case 'n' -> parseNull();
            default -> parseNumber(); // only digits/'-' remain
        };
    }

    // Parses { "key": value, ... }
    private Map<String, Object> parseObject() {
        Map<String, Object> map = new LinkedHashMap<>();
        pos++; // skip '{'
        skipWs();
        if (json.charAt(pos) == '}') { pos++; return map; } // empty object
        while (true) {
            skipWs();
            String key = parseString();
            skipWs();
            pos++; // skip ':'
            map.put(key, parseValue());
            skipWs();
            char c = json.charAt(pos++);
            if (c == '}') return map; // done
            if (c != ',') throw new IllegalArgumentException("Expected ',' or '}' at " + (pos - 1));
        }
    }

    // Parses [ value, value, ... ]
    private List<Object> parseArray() {
        List<Object> list = new ArrayList<>();
        pos++; // skip '['
        skipWs();
        if (json.charAt(pos) == ']') { pos++; return list; } // empty array
        while (true) {
            list.add(parseValue());
            skipWs();
            char c = json.charAt(pos++);
            if (c == ']') return list; // done
            if (c != ',') throw new IllegalArgumentException("Expected ',' or ']' at " + (pos - 1));
        }
    }

    // Parses a quoted string, resolving escape sequences.
    private String parseString() {
        pos++; // skip opening quote
        StringBuilder sb = new StringBuilder();
        while (true) {
            char c = json.charAt(pos++);
            if (c == '"') return sb.toString(); // closing quote
            if (c != '\\') { sb.append(c); continue; }
            char esc = json.charAt(pos++);
            switch (esc) {
                case 'n' -> sb.append('\n');
                case 't' -> sb.append('\t');
                case 'r' -> sb.append('\r');
                case 'b' -> sb.append('\b');
                case 'f' -> sb.append('\f');
                case '"', '\\', '/' -> sb.append(esc);
                case 'u' -> { // unicode escape, e.g. \u00e9
                    sb.append((char) Integer.parseInt(json.substring(pos, pos + 4), 16));
                    pos += 4;
                }
                default -> throw new IllegalArgumentException("Invalid escape at " + pos);
            }
        }
    }

    // Parses an integer or floating-point number.
    private Object parseNumber() {
        int start = pos;
        while (pos < json.length() && "-+.0123456789eE".indexOf(json.charAt(pos)) >= 0) pos++;
        String s = json.substring(start, pos);
        // decimal point or exponent -> double, otherwise try integer first
        if (s.indexOf('.') >= 0 || s.indexOf('e') >= 0 || s.indexOf('E') >= 0) return Double.parseDouble(s);
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return Double.parseDouble(s); // fallback for numbers too big for long
        }
    }

    private Boolean parseBoolean() {
        if (json.startsWith("true", pos)) { pos += 4; return Boolean.TRUE; }
        pos += 5;
        return Boolean.FALSE;
    }

    // Parses literal null.
    private Object parseNull() {
        pos += 4;
        return null;
    }

    private void skipWs() {
        while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) pos++;
    }
}