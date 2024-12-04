import { fireEvent, render, screen, getByText } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import Response from "../Response";
test("test addResponse ", () => {

  render(<BrowserRouter><Response /> </BrowserRouter>)
  const buttonElement = screen.getByText("Post");
  expect(buttonElement).toBeInTheDocument();
})
test("test validation", () => {
  render(<BrowserRouter><Response /> </BrowserRouter>)
  const responseAnswerInput = screen.getByPlaceholderText("Type your Response...")
  const buttonElement = screen.getByText("Post");
  fireEvent.click(buttonElement);
  expect(responseAnswerInput).toHaveTextContent("");
  expect(screen.getByText("Please enter your response")).toBeInTheDocument()
})

test("test addResponse", () => {
  render(<BrowserRouter><Response /> </BrowserRouter>)
  const responseAnswerInput = screen.getByPlaceholderText("Type your Response...");
  expect(responseAnswerInput).toBeInTheDocument();
})