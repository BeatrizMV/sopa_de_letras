package com.losdevdepaco.p7project.utils;

public class SopaLetrasRenderer {

	
	public static String renderizarTabla(char[][] tabla) {
		String htmlTabla = "<table id=\"sopa-letras\" class=\"sp__table\">";

		for (int i = 0; i < 15; i++) {
			htmlTabla += "<tr>";
			for (int j = 0; j < 20; j++) {
				htmlTabla += "<td class='celda' id=" + (i) + "_" + (j) + ">" + tabla[i][j]
						+ "</td>";
			}
			htmlTabla += "</tr>";
		}
		htmlTabla += "</table>";
		return htmlTabla;
	}
}
