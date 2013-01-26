/**
 * 
 */
package com.moberation.android.domain;

/**
 * @author jaran
 * 
 */
public class AvailableGame {

	private String id;

	private String role;

	private String name;

	public AvailableGame() {

	}

	public AvailableGame(final String id, final String role, final String name) {
		this.id = id;
		this.role = role;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
