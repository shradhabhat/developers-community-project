import { fireEvent, render, screen, getByText } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import AddProfile from "../developercomponent/AddProfile";
test("test addprofile ", () => {
  render(<BrowserRouter><AddProfile /> </BrowserRouter>)
  const btnElement = screen.getByText("Add");
  expect(btnElement).toBeInTheDocument();
})

test("test addprofile2", () => {
  render(<BrowserRouter><AddProfile /> </BrowserRouter>)
  const displayNameInput = screen.getByPlaceholderText("Display Name");
  expect(displayNameInput).toBeInTheDocument();
})

test("test validation", () => {
  render(<BrowserRouter><AddProfile /> </BrowserRouter>)
  const displayNameInput = screen.getByPlaceholderText("Display Name");
  const btnElement = screen.getByText("Add");
  fireEvent.click(btnElement);
  expect(displayNameInput).toHaveTextContent("");
  expect(screen.getByText("Please enter display name")).toBeInTheDocument()
})
