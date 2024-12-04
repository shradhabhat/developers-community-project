import { fireEvent, render, screen, getByText } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import SignUp from '../SignUp'
test("test signup", () => {
  render(<BrowserRouter><SignUp /> </BrowserRouter>)
  const btnElement = screen.getByText("Sign up");
  expect(btnElement).toBeInTheDocument();
})

test("test signup2", () => {
  render(<BrowserRouter><SignUp /> </BrowserRouter>)
  const userNameInput = screen.getByPlaceholderText("Username");
  expect(userNameInput).toBeInTheDocument();
})

test("test validation", () => {
  render(<BrowserRouter><SignUp /> </BrowserRouter>)
  const userNameInput = screen.getByPlaceholderText("Username");
  const btnElement = screen.getByText("Sign up");
  fireEvent.click(btnElement);
  expect(userNameInput).toHaveTextContent("");
  expect(screen.getByText("Please enter username")).toBeInTheDocument()
})
