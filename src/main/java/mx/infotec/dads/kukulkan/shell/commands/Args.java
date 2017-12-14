package mx.infotec.dads.kukulkan.shell.commands;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import com.beust.jcommander.Parameter;

public class Args {
	@Parameter
	private List<String> parameters = new ArrayList<>();

	@Min(3)
	@Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
	private Integer verbose = 1;

	@Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
	private String groups;

	@Parameter(names = "-debug", description = "Debug mode")
	private boolean debug = false;

	@Override
	public String toString() {
		return "Args{" + "parameters=" + parameters + ", verbose=" + verbose + ", groups='" + groups + '\'' + ", debug="
				+ debug + '}';
	}
}