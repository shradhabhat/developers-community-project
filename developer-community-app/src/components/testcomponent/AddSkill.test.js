import { fireEvent, render, screen, getByText } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import AddSkill from "../skillcomponents/AddSkill";


test("test addSkill ", () => {

  render(<BrowserRouter><AddSkill /> </BrowserRouter>)
  const buttonElement = screen.getByText("Add Skill");
  expect(buttonElement).toBeInTheDocument();
})


test("test validation", () => {
  render(<BrowserRouter><AddSkill /> </BrowserRouter>)
  const skillNameInput = screen.getByPlaceholderText("skill Name")
  const buttonElement = screen.getByText("Add Skill");
  fireEvent.click(buttonElement);
  expect(skillNameInput).toHaveTextContent("");
  expect(screen.getByText("Skill Name is required")).toBeInTheDocument()

})

test("test addSkill2", () => {
  render(<BrowserRouter><AddSkill /> </BrowserRouter>)
  const skillNameInput = screen.getByPlaceholderText("skill Name");
  expect(skillNameInput).toBeInTheDocument();
})
