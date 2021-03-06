import {ElementFinder, browser} from "protractor";
import {EC} from "./expected-conditions";
import {promise as wdpromise} from "selenium-webdriver";

export class ProtractorHelpers {
    readonly timeout: number = 5000;

    clickOnElement(element: ElementFinder) {
        browser.wait(EC.elementToBeClickable(element), this.timeout,
            `No element found using locator: ${element.locator()}`);
        element.click();
    }

    isElementPresence(element: ElementFinder): wdpromise.Promise<boolean> {
        browser.wait(EC.presenceOf(element), this.timeout,
            `No element found using locator: ${element.locator()}`);
        return element.isPresent();
    }

    isEnabledElement(element: ElementFinder): wdpromise.Promise<boolean> {
        browser.wait(EC.presenceOf(element), this.timeout,
            `No element found using locator: ${element.locator()}`);
        return element.isEnabled();
    }

    getElementText(element): Promise<string> {
        browser.wait(EC.presenceOf(element), this.timeout,
            `No element found using locator: ${element.locator()}`);
        return element.getText();
    }

    sendKeys(element: ElementFinder, value: string) {
        browser.wait(EC.elementToBeClickable(element), this.timeout,
            `No element found using locator: ${element.locator()}`);
        element.clear();
        element.sendKeys(value);
    }
}
