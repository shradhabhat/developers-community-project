import { fireEvent, render, screen, getByText } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import AddSkillMap from "../skillcomponents/AddSkillMap";


test("test skillMap ", () => {

  render(<BrowserRouter><AddSkillMap /> </BrowserRouter>)
  const buttonElement = screen.getByText("Add Skill");
  expect(buttonElement).toBeInTheDocument();
})


test("test validation", () => {
  render(<BrowserRouter><AddSkillMap /> </BrowserRouter>)
  const profeciencyLevelInput = screen.getByPlaceholderText("Profeciency Level")
  const buttonElement = screen.getByText("Add Skill");
  fireEvent.click(buttonElement);
  expect(profeciencyLevelInput).toBeInTheDocument();
  expect(screen.getByText("Profeciency Level is required")).toBeInTheDocument()

})

test("test skillMap2", () => {
  render(<BrowserRouter><AddSkillMap /> </BrowserRouter>)
  const profeciencyLevelInput = screen.getByPlaceholderText("Profeciency Level");
  expect(profeciencyLevelInput).toBeInTheDocument();
})
