import { fireEvent, render, screen, getByText } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import Login from "../Login";

test("test login ", () => {
  render(<BrowserRouter><Login /> </BrowserRouter>)
  const btnElement = screen.getByText("Login");
  expect(btnElement).toBeInTheDocument();
})

test("test login2", () => {
  render(<BrowserRouter><Login /> </BrowserRouter>)
  const usrNameInput = screen.getByPlaceholderText("Enter username");
  expect(usrNameInput).toBeInTheDocument();
})

test("test validation", () => {
  render(<BrowserRouter><Login /> </BrowserRouter>)
  const usrNameInput = screen.getByPlaceholderText("Enter username");
  const btnElement = screen.getByText("Login");
  fireEvent.click(btnElement);
  expect(usrNameInput).toHaveTextContent("");
  expect(screen.getByText("Please enter username")).toBeInTheDocument()
})
