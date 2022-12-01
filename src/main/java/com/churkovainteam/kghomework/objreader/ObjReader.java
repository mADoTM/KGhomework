package com.churkovainteam.kghomework.objreader;

import com.churkovainteam.kghomework.common.ObjToken;
import com.churkovainteam.kghomework.math.Vector2f;
import com.churkovainteam.kghomework.math.Vector3f;
import com.churkovainteam.kghomework.model.Model;
import com.churkovainteam.kghomework.model.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjReader {

	public static Model read(String fileContent) {
		Scanner scanner = new Scanner(fileContent);

		Model model = new Model();

		int fileLine = 1;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			List<String> wordsInLine = new ArrayList<>(List.of(line.split("\\s+")));
			if (wordsInLine.isEmpty()) {
				continue;
			}

			try {
				ObjToken token = ObjToken.fromString(wordsInLine.get(0));
				wordsInLine.remove(0);

				switch (token) {
					case VERTEX -> model.vertices.add(parseVertex(wordsInLine, fileLine));
					case TEXTURE -> model.textureVertices.add(parseTextureVertex(wordsInLine, fileLine));
					case NORMAL -> model.normals.add(parseNormal(wordsInLine, fileLine));
					case FACE -> model.polygons.add(parseFace(wordsInLine, fileLine));
				}
			} catch (IllegalArgumentException e) {
				throw new ObjReaderException(e.getMessage(), fileLine);
			}

			fileLine++;
		}

		return model;
	}

	protected static Vector3f parseVertex(List<String> wordsInLineWithoutToken, int fileLine) {
		if (wordsInLineWithoutToken.size() != 3) {
			throw new ObjReaderException("Error reading a vertex. Only x, y, z coordinates " +
					"should be present in the description.", fileLine);
		}

		try {
			return new Vector3f(
					Float.parseFloat(wordsInLineWithoutToken.get(0)),
					Float.parseFloat(wordsInLineWithoutToken.get(1)),
					Float.parseFloat(wordsInLineWithoutToken.get(2)));
		} catch(NumberFormatException e) {
			throw new ObjReaderException("Failed to parse float value.", fileLine);
		}
	}

	protected static Vector2f parseTextureVertex(List<String> wordsInLineWithoutToken, int fileLine) {
		if (wordsInLineWithoutToken.size() != 2) {
			throw new ObjReaderException("Error reading texture vertex. Only u, v arguments should be present" +
					"in the description.", fileLine);
		}

		try {
			return new Vector2f(
					Float.parseFloat(wordsInLineWithoutToken.get(0)),
					Float.parseFloat(wordsInLineWithoutToken.get(1)));
		} catch(NumberFormatException e) {
			throw new ObjReaderException("Failed to parse float value.", fileLine);

		}
	}

	protected static Vector3f parseNormal(List<String> wordsInLineWithoutToken, int fileLine) {
		if (wordsInLineWithoutToken.size() != 3) {
			throw new ObjReaderException("Error reading a normal. Only x, y, z coordinates " +
					"should be present in the description.", fileLine);
		}

		try {
			return new Vector3f(
					Float.parseFloat(wordsInLineWithoutToken.get(0)),
					Float.parseFloat(wordsInLineWithoutToken.get(1)),
					Float.parseFloat(wordsInLineWithoutToken.get(2)));
		} catch(NumberFormatException e) {
			throw new ObjReaderException("Failed to parse float value.", fileLine);
		}
	}

	protected static Polygon parseFace(List<String> wordsInLineWithoutToken, int fileLine) {
		List<Integer> polygonVertexIndices = new ArrayList<>();
		List<Integer> polygonTextureVertexIndices = new ArrayList<>();
		List<Integer> polygonNormalIndices = new ArrayList<>();

		for (String value : wordsInLineWithoutToken) {
			parseFaceWord(value, polygonVertexIndices, polygonTextureVertexIndices, polygonNormalIndices, fileLine);
		}

		Polygon polygon = new Polygon();
		polygon.setVertexIndices(polygonVertexIndices);
		polygon.setTextureVertexIndices(polygonTextureVertexIndices);
		polygon.setNormalIndices(polygonNormalIndices);

		return polygon;
	}

	protected static void parseFaceWord(
			String wordInLine,
			List<Integer> polygonVertexIndices,
			List<Integer> polygonTextureVertexIndices,
			List<Integer> polygonNormalIndices,
			int fileLine) {
		try {
			String[] wordIndices = wordInLine.split("/");
			switch (wordIndices.length) {
				case 1 -> polygonVertexIndices.add(Integer.parseInt(wordIndices[0]) - 1);
				case 2 -> {
					polygonVertexIndices.add(Integer.parseInt(wordIndices[0]) - 1);
					polygonTextureVertexIndices.add(Integer.parseInt(wordIndices[1]) - 1);
				}
				case 3 -> {
					polygonVertexIndices.add(Integer.parseInt(wordIndices[0]) - 1);
					polygonNormalIndices.add(Integer.parseInt(wordIndices[2]) - 1);
					if (!wordIndices[1].equals("")) {
						polygonTextureVertexIndices.add(Integer.parseInt(wordIndices[1]) - 1);
					}
				}
				default -> throw new ObjReaderException("Invalid polygon vertex description.", fileLine);
			}

		} catch(NumberFormatException e) {
			throw new ObjReaderException("Failed to parse int value.", fileLine);

		} catch(IndexOutOfBoundsException e) {
			throw new ObjReaderException("Invalid polygon description.", fileLine);
		}
	}
}
